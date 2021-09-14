package dev.patika.quixotic95.schoolmanagementsystem.entity.logger;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
@Component
@SessionScope
public class ClientInfo {

    private String clientIpAddress;

    private String clientRequestUrl;

    private String clientSessionId;

}
