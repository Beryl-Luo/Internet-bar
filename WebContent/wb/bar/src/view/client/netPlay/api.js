import {server} from '@/tools/servers'

export class LoginApi {
  static Login (data) {
    return server.connection('POST', '/LoginServlet', data)
  }

  static SelectMessageByComputerId (data) {
    return server.connection('POST', '/UserServlet', data)
  }

  static UpdataBalance (data) {
    return server.connection('POST', '/UserServlet', data)
  }

  static Checkout (data) {
    return server.connection('GET', '/UserServlet', data)
  }
}
