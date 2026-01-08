package studydocs.media.api.dto.view;

import lombok.Builder;

@Builder
public record PreviewDataView(String baseUrl, String key) {
}
