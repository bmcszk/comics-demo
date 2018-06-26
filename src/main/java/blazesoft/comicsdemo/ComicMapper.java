package blazesoft.comicsdemo;

import org.springframework.stereotype.Component;

@Component
public class ComicMapper {
    public ComicDto mapToDto(ComicEntity entity) {
        var result = new ComicDto();
        result.setId(entity.getId());
        result.setNum(entity.getNum());
        result.setTitle(entity.getTitle());
        result.setTranscript(entity.getTranscript());
        result.setImg(entity.getImg());
        return result;
    }

    public ComicEntity mapToEntity(ComicDto dto) {
        var result = new ComicEntity();
        result.setId(dto.getId());
        result.setNum(dto.getNum());
        result.setTitle(dto.getTitle());
        result.setTranscript(dto.getTranscript());
        result.setImg(dto.getImg());
        return result;
    }

}
