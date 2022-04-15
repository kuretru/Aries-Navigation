import BaseSequenceService from '../base-sequence-service';

class WebTagService extends BaseSequenceService<API.Web.WebTagDTO, API.Web.WebTagQuery> {
  constructor() {
    super('/tags');
  }
}

export default WebTagService;
