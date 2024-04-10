package com.example.reactboard.domain.article.member.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image save(String name, String path) {
        Image image = new Image();
        image.setName(name);
        image.setPath(path);
        return imageRepository.save(image);
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow();
    }
}
