package com.tencoding.ADayOfLearning.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tencoding.ADayOfLearning.repository.interfaces.LecturePhotoRepository;
import com.tencoding.ADayOfLearning.repository.model.LecturePhoto;

@Service
public class LecturePhotoService {

	@Autowired
	LecturePhotoRepository lecturePhotoRepository;

	public List<LecturePhoto> getLecturePhotosById(Integer id) {
		return lecturePhotoRepository.findByLectureId(id);
	}

	public void insertLecturePhotos(MultipartFile[] files, int registeredLectureId) {
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				insertLecturePhoto(file, registeredLectureId);
			}
		}
	}

	public void insertLecturePhoto(MultipartFile file, int registeredLectureId) {
		try {
			byte[] bytes = file.getBytes();
			// 파일 저장 경로 설정 *(추후 수정)*
			String uploadDir = "C:\\workspace\\spring\\ADayOfLearning\\src\\main\\resources\\static\\images\\lectureImages\\";
			// sql에 올릴 경로
			String sqlPath = "/images/lectureImages/";
			// 파일 이름 : UUID 형식(128비트 길이의 고유 식별자)
			String identifier = Integer.toString(registeredLectureId) + "_";
			String fileName = identifier + UUID.nameUUIDFromBytes(file.getOriginalFilename().getBytes()) + ".jpg";

			// 서버 로컬폴더에 업로드
			java.nio.file.Path path = Paths.get(uploadDir + fileName);
			System.out.println(Files.write(path, bytes));

			// sql 업로드
			LecturePhoto lecturePhoto = LecturePhoto.builder().isThumbnail(false).lectureId(registeredLectureId)
					.img(sqlPath + fileName).build();

			lecturePhotoRepository.insert(lecturePhoto);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
