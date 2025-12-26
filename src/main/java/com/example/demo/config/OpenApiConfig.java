// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI openAPI() {
//         Server server = new Server();
//         server.setUrl("https://9137.32procr.amypo.ai/");
//         server.setDescription("AmyPo Server");

//         return new OpenAPI()
//                 .servers(List.of(server));
//     }
// }
package com.example.demo.config;

import org.springframework.context.annotation.*;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentSkillGapApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Skill Gap Analyzer API")
                        .description("Swagger UI for Skill Gap Analyzer")
                        .version("1.0"));
    }
}
