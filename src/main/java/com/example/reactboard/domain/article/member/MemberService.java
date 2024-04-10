package com.example.reactboard.domain.article.member;

import com.example.reactboard.domain.article.member.image.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(String username) {

        Member member = new Member();
        member.setUsername(username);
        Image image = new Image();
        image.setPath("file:///C:/work/java/react-board/images/profile/noImg.jpg");
        member.setImage(image);

        return memberRepository.save(member);
    }

    public Member getMemberInfo(Long id) {

        return memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 존재하지 않습니다.")
        );
    }

    public void updateImage(Member member, MultipartFile image) {
        try {

            Image newImage = new Image();

            String uuid = UUID.randomUUID().toString();
            newImage.setPath("http://localhost:8088/profile/newImg_%s.jpg".formatted(uuid));
            image.transferTo(new File("C:/work/java/react-board/images/profile/newImg_%s.jpg".formatted(uuid)));
            member.setImage(newImage);
            memberRepository.save(member);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
