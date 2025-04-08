package seminario.practica.sgi.incident.infrastructure.http.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import seminario.practica.sgi.common.UserDataDetails;
import seminario.practica.sgi.common.userdata.IFindByEmail;
import seminario.practica.sgi.common.userdata.UserDataResponse;
import seminario.practica.sgi.incident.application.service.usecase.*;
import seminario.practica.sgi.incident.infrastructure.http.mapper.IncidentDomainMapper;
import seminario.practica.sgi.incident.infrastructure.http.mapper.IncidentResponseMapper;
import seminario.practica.sgi.incident.infrastructure.http.request.AssignIncidentRequest;
import seminario.practica.sgi.incident.infrastructure.http.request.CreateIncidentRequest;
import seminario.practica.sgi.incident.infrastructure.http.request.IncidentCustomerRequest;
import seminario.practica.sgi.incident.infrastructure.http.request.UpdateIncidentRequest;
import seminario.practica.sgi.incident.infrastructure.http.response.IncidentResponse;
import seminario.practica.sgi.user.application.service.usecase.IListUserUseCase;
import seminario.practica.sgi.user.domain.Role;
import seminario.practica.sgi.user.infrastructure.http.mapper.UserDomainMapper;
import seminario.practica.sgi.user.infrastructure.http.response.UserResponse;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IncidentController {

    private final IFindIncidentsByUsernameUseCase listIncidentsByUsernameUseCase;

    private final ICreateIncidentUseCase createIncidentUseCase;

    private final IFindByEmail getUser;

    private final IFindIncidentByIdUseCase findIncidentByIdUseCase;

    private final IFindIncidentsUseCase findIncidentsUseCase;

    private final IncidentDomainMapper incidentDomainMapper;

    private final IncidentResponseMapper incidentResponseMapper;

    private final UserDomainMapper userDomainMapper;

    private final IListUserUseCase listUserUseCase;

    private final IAssignOperatorUseCase assignOperatorUseCase;

    private final IFindIncidentsByOperatorUseCase findIncidentsByOperatorUseCase;

    private final IUpdateIncidentUseCase updateIncidentUseCase;

    @PreAuthorize("hasRole('COORDINATOR')")
    @GetMapping("/coordinator")
    public String coordinatorPanel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDataResponse userResponse = getUser.findByEmail(userDetails.getUsername());
        List<IncidentResponse> incidents = incidentResponseMapper.toDomain(findIncidentsUseCase.findAllIncidents());
        model.addAttribute("user", userResponse);
        model.addAttribute("incidents", incidents);
        return "coordinator/coordinator_panel";
    }

    @PreAuthorize("hasRole('COORDINATOR')")
    @GetMapping("/incident/coordinator/{id}")
    public String getAssignIncident(@PathVariable Long id, Model model) {
        IncidentResponse incident = incidentResponseMapper.toDomain(findIncidentByIdUseCase.findById(id));
        List<UserResponse> operators = userDomainMapper.toResponse(listUserUseCase.findByRole(Role.OPERATOR));
        model.addAttribute("incident", incident);
        model.addAttribute("operators", operators);
        return "incident/assign_incident";
    }

    @PreAuthorize("hasRole('COORDINATOR')")
    @PostMapping("/incident/coordinator/assign")
    public String assignIncident(@ModelAttribute("assignRequest") AssignIncidentRequest assignRequest) {
        System.out.println(assignRequest.getIncidentId());
        System.out.println(assignRequest.getOperatorId());
        System.out.println(assignRequest.getPriority());
        assignOperatorUseCase.assignOperator(incidentDomainMapper.toDomain(assignRequest));
        return "redirect:/coordinator";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer")
    public String customerPanel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<IncidentResponse> incidents = incidentResponseMapper.toDomain(listIncidentsByUsernameUseCase.findByUsername(userDetails.getUsername()));
        UserDataResponse userResponse = getUser.findByEmail(userDetails.getUsername());
        model.addAttribute("user", userResponse);
        model.addAttribute("incidents", incidents);
        return "customer/customer_panel";
    }

    @GetMapping("/incident/create_incident")
    public String showCreateIncidentForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDataResponse userResponse = getUser.findByEmail(userDetails.getUsername());
        model.addAttribute("user", userResponse);
        model.addAttribute("createIncidentRequest", new CreateIncidentRequest());
        return "incident/create_incident";
    }

    @PostMapping("/incident/create_incident")
    public String createIncident(@ModelAttribute("createIncidentRequest") CreateIncidentRequest incidentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDataResponse userResponse = getUser.findByEmail(userDetails.getUsername());
        incidentRequest.setCustomer(new IncidentCustomerRequest(userResponse.getId()));
        createIncidentUseCase.add(incidentDomainMapper.toDomain(incidentRequest));
        return "redirect:/customer";
    }

    @GetMapping("/incident/{id}")
    public String getIncidentDetail(@PathVariable Long id, Model model) {
        IncidentResponse incident = incidentResponseMapper.toDomain(findIncidentByIdUseCase.findById(id));
        model.addAttribute("incident", incident);
        return "incident/incident_detail";
    }

    @PreAuthorize("hasRole('OPERATOR')")
    @GetMapping("/operator")
    public String operatorPanel(Principal principal, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principalObj = authentication.getPrincipal();

        Long operatorId;
        if (principalObj instanceof UserDataDetails userDetails) {
            operatorId = userDetails.getUserId();
        } else if (principalObj instanceof org.springframework.security.core.userdetails.User user) {
            UserDataResponse userResponse = getUser.findByEmail(user.getUsername());
            operatorId = userResponse.getId();
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principalObj.getClass());
        }

        List<IncidentResponse> incidents = incidentResponseMapper.toDomain(findIncidentsByOperatorUseCase.findByOperator(operatorId));
        UserDataResponse userResponse = getUser.findByEmail(authentication.getName());
        model.addAttribute("user", userResponse);
        model.addAttribute("incidents", incidents);

        return "operator/operator_panel";
    }

    @PreAuthorize("hasRole('OPERATOR')")
    @GetMapping("operator/incident/{id}")
    public String getIncidentToUpdate(@PathVariable Long id, Model model) {
        IncidentResponse incident = incidentResponseMapper.toDomain(findIncidentByIdUseCase.findById(id));
        model.addAttribute("incident", incident);
        return "incident/update_incident";
    }

    @PreAuthorize("hasRole('OPERATOR')")
    @PostMapping("/incident/operator/update")
    public String updateIncident(@ModelAttribute("updateRequest") UpdateIncidentRequest updateRequest) {
        updateIncidentUseCase.updateIncident(incidentDomainMapper.toDomain(updateRequest));
        return "redirect:/operator";
    }

}
