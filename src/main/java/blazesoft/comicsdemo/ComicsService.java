package blazesoft.comicsdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ComicsService {
    private final Log log = LogFactory.getLog(ComicsService.class);

    private final ComicsRepository comicsRepository;
    private final ComicMapper comicMapper;
    private final ComicsImportService comicsImportService;
    private final Random random = new Random();

    public ComicsService(ComicsRepository comicsRepository, ComicMapper comicMapper, ComicsImportService comicsImportService) {
        this.comicsRepository = comicsRepository;
        this.comicMapper = comicMapper;
        this.comicsImportService = comicsImportService;
        init();
    }

    public void init() {
        log.info("init()");
        comicsRepository.count()
                .filter(c -> c == 0)
                .subscribe(c -> initializeDb());
    }

    private void initializeDb() {
        comicsImportService.importComics()
                .map(c -> comicMapper.mapToEntity(c))
                .flatMap(e -> comicsRepository.save(e))
                .subscribe();
    }


    public Mono<ComicDto> getRandom() {
        //TODO change to Mongo $sample
        return comicsRepository.findAll()
                .map(e -> e.getId())
                .collect(Collectors.toList())
                .map(l -> l.get(random.nextInt(l.size())))
                .flatMap(i -> comicsRepository.findById(i))
                .map(e -> comicMapper.mapToDto(e));
    }

    public Mono<ComicDto> getById(String comicId) {
        return comicsRepository.findById(comicId)
                .map(e -> comicMapper.mapToDto(e));
    }

    public Flux<ComicDto> getAll() {
        return comicsRepository.findAll()
                .map(e -> comicMapper.mapToDto(e));
    }

    public Mono<ComicDto> add(ComicDto comic) {
        return comicsRepository.save(comicMapper.mapToEntity(comic))
                .map(e -> comicMapper.mapToDto(e));
    }
}
