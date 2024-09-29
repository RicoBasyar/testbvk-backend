    package com.example.testbvk.DTO.reqbody;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class LoginUserReqBody {
        private String email;
        private String password;
    }
