import BaseSequenceService from '../base-sequence-service';

class WebStieService extends BaseSequenceService<API.Web.WebSiteDTO, API.Web.WebSiteQuery> {
  constructor() {
    super('/sites');
  }
}

export default WebStieService;
