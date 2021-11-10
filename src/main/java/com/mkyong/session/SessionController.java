package com.mkyong.session;

import com.mkyong.auth.Auth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class SessionController {

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/session/validate")
    ResponseEntity validateSession(@Valid @RequestBody Session session) {
        String sessionId = "Session not found or expired";
        HashMap<String, String> map = new HashMap<>();
        Map.Entry<String, String> userData = SessionRepository.validateSession(session);
        if (userData!=null){
            map.put("status", "ok");
            map.put("user", userData.getKey());
            map.put("access", userData.getValue());
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }   else {
            //map.put("error", sessionId);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
            throw new ResponseStatusException(NOT_FOUND, sessionId);
        }
    }
}
