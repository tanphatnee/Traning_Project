package d9.traning_project.controller;

import d9.traning_project.exception.LoginException;
import d9.traning_project.model.domain.Users;
import d9.traning_project.model.dto.request.FormSignInDto;
import d9.traning_project.model.dto.request.FormSignUpDto;
import d9.traning_project.model.dto.response.JwtResponse;
import d9.traning_project.repository.IUserRepository;
import d9.traning_project.security.jwt.JwtProvider;
import d9.traning_project.security.user_principle.UserPrinciple;
import d9.traning_project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/v4/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IUserService userService;
    @Autowired
    IUserRepository userRepository;
//    @Autowired
//    private MailService mailService;

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("vào được rồi nè");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signin(@RequestBody FormSignInDto formSignInDto, HttpSession session) throws LoginException {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(formSignInDto.getEmail(), formSignInDto.getPassword())
            ); // tạo đối tương authentiction để xác thực thông qua username va password
//            session.setAttribute("currentUser", authentication);
        } catch (AuthenticationException e) {
            throw new LoginException("Email or password is incorrect");
        }           // tạo token và trả về cho người dùng

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        if (!userPrinciple.isStatus()){
            throw  new LoginException("Your account has been blocked");
        }
        String token = jwtProvider.generateToken(userPrinciple);
        // lấy ra users principle
        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(JwtResponse.builder().token(token)
                .fullName(userPrinciple.getFullName())
                .email(userPrinciple.getEmail())
                .phone(userPrinciple.getPhone())
                .roles(roles)
                .type("Bearer")
                .status(userPrinciple.isStatus()).build());
    }

    @PostMapping("/sign-up")
    private ResponseEntity<String> signup(@Valid @RequestBody FormSignUpDto formSignUpDto) {
        List<Users> usersList = userRepository.findAll();
        for (Users user : usersList
        ) {
            if (user.getEmail().equals(formSignUpDto.getEmail())) {
                return new ResponseEntity<>("Username already existed", HttpStatus.BAD_REQUEST);
            }
//            else if (user.getEmail().equals(formSignUpDto.getEmail())) {
//                return new ResponseEntity<>("Email already register", HttpStatus.BAD_REQUEST);
//            }

        }
        userService.save(formSignUpDto);
//        mailService.sendEmail(formSignUpDto.getEmail(),"Register Successfully","Register Successfully");
        return new ResponseEntity("Register successful", HttpStatus.CREATED);

    }
}
