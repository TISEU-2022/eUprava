package io.uvera.springauthintegrationdemo.demo

import org.springframework.http.ResponseEntity.ok
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/example")
class ExampleController {
    @GetMapping
    fun getSelf(@AuthenticationPrincipal userDetails: CustomUserDetails) = ok(userDetails)
}
