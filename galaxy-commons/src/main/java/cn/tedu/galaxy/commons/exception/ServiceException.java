package cn.tedu.galaxy.commons.exception;

public class ServiceException extends RuntimeException {

    private ServiceCode serviceCode;

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

}
