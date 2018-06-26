package blazesoft.comicsdemo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ComicsRepository extends ReactiveCrudRepository<ComicEntity, String> {
}
