package blazesoft.comicsdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class XkcdComicsImportServiceImpl implements ComicsImportService {
    private final Log log = LogFactory.getLog(XkcdComicsImportServiceImpl.class);

    private final WebClient webClient;
    private static final int MAX = 10;

    public XkcdComicsImportServiceImpl() {
        webClient = WebClient.create("https://xkcd.com");
    }

    @Override
    public Flux<ComicDto> importComics() {
        log.info("importComics()");
        Publisher<ComicDto>[] result = new Publisher[MAX];
        for (int i = 0; i < MAX; i++) {
            result[i] = webClient.get()
                    .uri(String.format("/%d/info.0.json", (i + 1)))
                    .retrieve()
                    .bodyToMono(ComicDto.class);
        }

        return Flux.concat(result);
    }
}
