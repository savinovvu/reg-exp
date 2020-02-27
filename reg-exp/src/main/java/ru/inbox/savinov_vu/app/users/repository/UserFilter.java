package ru.inbox.savinov_vu.app.users.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import ru.inbox.savinov_vu.app.users.dto.UserFilterDto;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.model.User_;
import ru.inbox.savinov_vu.common.classes.CriteriaApiFilter;

import static java.util.Objects.isNull;
import static ru.inbox.savinov_vu.common.util.StringUtils.isNullOrEmpty;



@NoArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Getter
public class UserFilter extends CriteriaApiFilter<User> {

  private int page;

  private int size;

  private Sort sort;


  public static UserFilter of(UserFilterDto filterDto) {
    UserFilter filter = of();
    filter.byId(filterDto.getId());
    filter.byFirstName(filterDto.getFirstName());
    filter.byLastName(filterDto.getLastName());
    filter.byEmail(filterDto.getEmail());
    filter.bySex(filterDto.getSex());
    filter.byBirthDate(filterDto.getBirthDate());
    Boolean enabled = filterDto.getEnabled();
    filter.enabled(isNull(enabled) ? true : enabled);
    filter.page = filterDto.getPage() - 1;
    filter.size = filterDto.getSize();
    filter.setSort(filterDto);
    return filter;
  }


  private void byId(Integer id) {
    if (isNull(id)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.ID), id));
    addCondition(condition);
  }


  private void byFirstName(String firstName) {
    if (isNullOrEmpty(firstName)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.FIRST_NAME), firstName));
    addCondition(condition);
  }


  private void byLastName(String lastName) {
    if (isNullOrEmpty(lastName)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.LAST_NAME), lastName));
    addCondition(condition);
  }


  private void byEmail(String email) {
    if (isNullOrEmpty(email)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.EMAIL), email));
    addCondition(condition);
  }


  private void byLogin(String login) {
    if (isNullOrEmpty(login)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.LOGIN), login));
    addCondition(condition);
  }


  private void bySex(String sex) {
    if (isNullOrEmpty(sex)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.SEX), sex));
    addCondition(condition);
  }


  private void byBirthDate(String birthDate) {
    if (isNullOrEmpty(birthDate)) {
      return;
    }
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.BIRTH_DATE), birthDate));
    addCondition(condition);
  }


  private void enabled(Boolean enabled) {
    Specification<Specification> condition = Specification.where((r, cq, cb) -> cb.equal(r.get(User_.ENABLED), enabled));
    addCondition(condition);
  }


  private void setSort(UserFilterDto filterDto) {
    if (isNullOrEmpty(filterDto.getSort()) || isNullOrEmpty(filterDto.getDirection())) {
      defaultSort();
      return;
    }

    Sort.Direction direction;
    if (Sort.Direction.DESC.toString().equals(filterDto.getDirection())) {
      direction = Sort.Direction.DESC;
    } else {
      direction = Sort.Direction.ASC;
    }

    sort = Sort.by(direction, filterDto.getSort());
  }


  private void defaultSort() {
    sort = Sort.by(Sort.Direction.ASC, User_.ID);
  }

}
