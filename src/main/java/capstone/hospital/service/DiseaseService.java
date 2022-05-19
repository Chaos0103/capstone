package capstone.hospital.service;

import capstone.hospital.domain.KCDCode;
import capstone.hospital.repository.KCDCodeQueryRepository;
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
    private final KCDCodeQueryRepository kcdCodeQueryRepository;

    public List<KCDCode> list(String name) {
        return kcdCodeQueryRepository.findByName(name);
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
}
