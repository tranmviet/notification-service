package vn.com.itechcorp.notification.api.service;

import vn.com.itechcorp.base.service.AuditableDtoService;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.api.service.dto.SenderDTOGet;

public interface SenderService extends AuditableDtoService<SenderDTOGet, Sender, Long> {
}
