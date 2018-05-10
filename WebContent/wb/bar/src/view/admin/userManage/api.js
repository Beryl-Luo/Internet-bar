import {server} from '@/tools/servers'

export class UserApi {
  static AddUser (data) {
    return server.connection('POST', '/UserServlet', data)
  }

  static GetAllUserMember (data) {
    return server.connection('GET', '/MemberServlet', data)
  }

  static GetAllUser (data) {
    return server.connection('GET', '/UserServlet', data)
  }

  static GetUserTotal (data) {
    return server.connection('GET', '/UserServlet', data)
  }

  static EditUser (data) {
    return server.connection('POST', '/UserServlet', data)
  }

  static DeleteUser (data) {
    return server.connection('GET', '/UserServlet', data)
  }

  static GetUserById (data) {
    return server.connection('GET', '/UserServlet', data)
  }
}
