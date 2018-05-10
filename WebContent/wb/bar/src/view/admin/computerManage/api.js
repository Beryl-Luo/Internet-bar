import {server} from '@/tools/servers'

export class ComputerApi {
  static AddComputer (data) {
    return server.connection('POST', '/Computer', data)
  }

  static GetAllComputerArea () {
    return server.connection('GET', '/ComputerAreaServlet')
  }

  static GetAllComputer (data) {
    return server.connection('GET', '/Computer', data)
  }

  static GetComputerTotal (data) {
    return server.connection('GET', '/Computer', data)
  }

  static EditComputer (data) {
    return server.connection('POST', '/Computer', data)
  }

  static DeleteComputer (data) {
    return server.connection('GET', '/Computer', data)
  }

  static GetComputerById (data) {
    return server.connection('GET', '/Computer', data)
  }

  static Checkout (data) {
    return server.connection('GET', '/UserServlet', data)
  }
}
