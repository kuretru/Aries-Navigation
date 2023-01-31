import BaseSequenceService from '../base-sequence-service';

class WebCategoryService extends BaseSequenceService<API.Web.WebCategoryDTO, API.Web.WebCategoryQuery> {
  constructor() {
    super('/categories');
  }
}

export default WebCategoryService;
