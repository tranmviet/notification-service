package vn.com.itechcorp.notification.api.service;

import vn.com.itechcorp.base.service.AuditableDtoService;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;
import vn.com.itechcorp.notification.api.service.dto.ReceiverDTOGet;

public interface ReceiverService extends AuditableDtoService<ReceiverDTOGet, Receiver, Long> {
}
