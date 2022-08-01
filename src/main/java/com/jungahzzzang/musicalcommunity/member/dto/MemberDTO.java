package com.jungahzzzang.musicalcommunity.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class MemberDTO {
	
	private Long mberId;
	
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	@Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
	private String name;
	
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
	private String password;
	
	private Role role;
	
	//암호화된 password
	public void encryptPassword(String BCryptpassword) {
		this.password=BCryptpassword;
	}
	
	public Member dtoToEntity() {
		Member member = Member.builder()
				.name(name)
				.email(email)
				.password(password)
				.role(role)
				.build();
		return member;
	}

}
