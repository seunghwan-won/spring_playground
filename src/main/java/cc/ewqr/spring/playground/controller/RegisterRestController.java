package cc.ewqr.spring.playground.controller;

import cc.ewqr.spring.playground.repository.RegisterUseCase;
import cc.ewqr.spring.playground.model.User;
import cc.ewqr.spring.playground.model.UserResource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegisterRestController {
    private  RegisterUseCase registerUseCase;

    public RegisterRestController() {
    }

    public RegisterRestController(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/forums/{forumId}/register")
    UserResource register(
            @PathVariable(name = "forumId") Long forumId,
            @Valid @RequestBody UserResource userResource,
            @RequestParam(name = "sendWelcomeEmail") boolean sendWelcomeEmail) {
        User user = new User(
                userResource.getName(),
                userResource.getEmail());
        Long userId = registerUseCase.registerUser(user, sendWelcomeEmail);
        return new UserResource(
                userId,
                user.getName(),
                user.getEmail());
    }
}
