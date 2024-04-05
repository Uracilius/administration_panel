package adminpage.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedRequest {
    private int page;
    private int pageSize;
}
