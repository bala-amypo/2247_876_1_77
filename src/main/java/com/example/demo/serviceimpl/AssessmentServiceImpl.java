@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {

        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score required");
        }

        if (result.getMaxScore() == null) {
            result.setMaxScore(100.0);
        }

        if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score invalid");
        }

        return repository.save(result);
    }
}
