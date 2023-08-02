package vn.com.itechcorp.notification.api.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.filter.BaseFilter;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MessageTypeFilter extends BaseFilter<MessageType, String> {

    @JsonProperty("name")
    private String searchName;

    @Override
    public Predicate toPredicate(Root<MessageType> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        Predicate superPredicate = super.toPredicate(root, query, builder);
        if (superPredicate != null) predicates.add(superPredicate);

        if (searchName != null && !searchName.isEmpty()) {
            predicates.add(builder.like(root.get(MessageType_.NAME), "%" + searchName + "%"));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
