package capstone.hospital.service;

import capstone.hospital.domain.KCDCode;
import capstone.hospital.dto.DiseaseDto;
import capstone.hospital.exception.AppointmentException;
import capstone.hospital.exception.SearchException;
import capstone.hospital.repository.KCDCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiseaseService {

    private final KCDCodeRepository kcdCodeRepository;

    public List<KCDCode> list(String name) {
        return kcdCodeRepository.findByName(name);
    }

    /**
     * delete
     */
    @Transactional
    public void delete(String code) {
        kcdCodeRepository.deleteById(code);
    }

    @Transactional
    public void save(String code, String name) {
        kcdCodeRepository.save(new KCDCode(code, name));
    }

    public KCDCode findEditCode(String code) {
        Optional<KCDCode> findCode = kcdCodeRepository.findById(code);
        if (findCode.isPresent()) {
            return findCode.get();
        } else {
            throw new IllegalStateException("error");
        }
    }

    public DiseaseDto findCode(String name) {
        List<KCDCode> findCode = kcdCodeRepository.findByName(name);
        if (!findCode.isEmpty()) {
            return new DiseaseDto(findCode.get(0));
        } else {
            throw new SearchException("찾으시는 질병은 등록되지 않은 질병입니다.");
        }
    }

    public int count() {
        return (int) kcdCodeRepository.count();
    }
}
