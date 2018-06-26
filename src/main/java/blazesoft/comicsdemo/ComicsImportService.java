package blazesoft.comicsdemo;

import reactor.core.publisher.Flux;

public interface ComicsImportService {
    Flux<ComicDto> importComics();
}
