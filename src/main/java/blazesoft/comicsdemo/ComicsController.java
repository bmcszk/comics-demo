package blazesoft.comicsdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ComicsController {
    private final Log log = LogFactory.getLog(ComicsController.class);

    private final ComicsService comicsService;

    public ComicsController(ComicsService comicsService) {
        this.comicsService = comicsService;
    }

    @GetMapping(path = "/random",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ComicDto> getRandom() {
        log.info("getRandom()");
        return comicsService.getRandom();
    }

    @GetMapping(path = "/comics/{comicId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ComicDto> getById(@PathVariable String comicId) {
        log.info("getById()");
        return comicsService.getById(comicId);
    }

    @GetMapping(path = "/comics",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ComicDto> getAll() {
        log.info("getAll()");
        return comicsService.getAll();
    }

    @PostMapping(path = "/comics",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ComicDto> add(@RequestBody ComicDto comic) {
        log.info("add()");
        return comicsService.add(comic);
    }
}
