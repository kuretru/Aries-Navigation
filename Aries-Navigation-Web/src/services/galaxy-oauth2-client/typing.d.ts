// @ts-ignore
/* eslint-disable */

declare namespace Galaxy.OAuth2.Client {
  type OAuth2AuthorizeRequestDTO = {
    scopes?: string[];
    redirectUri?: string;
  };

  type OAuth2AuthorizeResponseDTO = {
    code: string;
    state: string;
  };
}

declare namespace Galaxy.OAuth2.System {
  type UserDTO = API.BaseDTO & {
    nickname: string;
    avatar: string;
    geminiId: string;
  };

  type UserLoginDTO = {
    userId: string;
    accessToken: {
      id: string;
      secret: string;
    };
  };
}
