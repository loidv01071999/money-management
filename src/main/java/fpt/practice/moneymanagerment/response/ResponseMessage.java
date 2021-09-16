package fpt.practice.moneymanagerment.response;

import fpt.practice.moneymanagerment.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    InvalidAccessError("99", "api.error.response.invalid.access")
    , DefaultInternalServerMessageError("100", "api.error.response.default.internal.message.error")
    , DuplicatedUsername("101", "api.error.response.duplicated.username")
    , UserRoleNotFound("102", "api.error.response.user.role.not.found")
    , CardIdNotFound("103", "api.error.response.card.id.not.found")
    , AuthenticateUserFailed("104", "api.error.response.authenticate.user.failed")
    , CreateStoreSuccessfully("105", "api.success.response.create.store.successfully")
    , UpdateStoreSuccessfully("106", "api.success.response.update.store.successfully")
    , DeleteStoreSuccessfully("107", "api.success.response.delete.store.successfully")
    , RegisterUserSuccessfully("108", "api.success.response.create.user.successfully")
    , AccountIdNotFound("109", "api.error.response.account.id.not.found")
    , TypeSpendingIdNotFound("110", "api.error.response.type.spending.id.not.found")
    , SubTypeSpendingIdNotFound("111", "api.error.response.sub.type.spending.id.not.exist")
    , UnitIdNotFound("112", "api.error.response.unit.id.not.found")
    , SpendingIdNotFound("113", "api.error.response.spending.id.not.found")
    , PasswordWrongFormat("114", "api.error.response.password.wrong.format")
    , EmailWrongFormat("115", "api.error.response.email.wrong.format")
    ;
    private String code;
    private String messageId;

    public String getMessage(Object... params) {
        String message = MessageUtils.getMessage(this.messageId, params);
        return message;
    }
}