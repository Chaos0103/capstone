package capstone.hospital.service;

import capstone.hospital.domain.ATCCode;
import capstone.hospital.domain.enumtype.ATCType;
import capstone.hospital.repository.ATCCodeQueryRepository;
import capstone.hospital.repository.ATCCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicineService {

    private final ATCCodeRepository atcCodeRepository;
    private final ATCCodeQueryRepository atcCodeQueryRepository;

    public List<ATCCode> list(String name) {
        return atcCodeQueryRepository.findByName(name);
    }

    public ATCCode findEditCode(String code) {
        Optional<ATCCode> findCode = atcCodeRepository.findById(code);
        if (findCode.isPresent()) {
            return findCode.get();
        } else {
            throw new IllegalStateException("error");
        }
    }

    /**
     * delete
     */
    @Transactional
    public void delete(String code) {
        atcCodeRepository.deleteById(code);
    }

    /**
     * save
     */
    @Transactional
    public void save(String code, String name, String company, ATCType type, int stock) {
        atcCodeRepository.save(new ATCCode(code, name, company, type, stock));
    }
}
