package ru.inbox.savinov_vu.test_helpers.data.factories.user;

import org.springframework.data.domain.Sort;
import ru.inbox.savinov_vu.app.users.dto.UserFilterDto;
import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.app.users.model.User_;

import java.time.LocalDate;

import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.LocalDateConstant.GAGARINS_FLY_DAY_LOCAL_DATE;



public class UserFilterDtoFactory {


  public static UserFilterDto of() {
    UserFilterDto userFilterDto = new UserFilterDto();
    userFilterDto.setSize(40);
    return userFilterDto;
  }


  public static UserFilterDto of(boolean enabled) {
    UserFilterDto userFilterDto = new UserFilterDto();
    userFilterDto.setSize(40);
    userFilterDto.setEnabled(enabled);
    return userFilterDto;
  }


  public static UserFilterDto of(int size) {
    UserFilterDto userFilterDto = new UserFilterDto();
    userFilterDto.setSize(size);
    return userFilterDto;
  }


  public static UserFilterDto ofEnabledSortedASCByParam(String param) {
    UserFilterDto userFilterDto = of(true);
    userFilterDto.setSort(param);
    userFilterDto.setDirection(Sort.Direction.ASC.toString());
    return userFilterDto;
  }


  public static UserFilterDto getEnabled() {
    UserFilterDto userFilterDto = of(true);
    return userFilterDto;
  }


  public static UserFilterDto getDisabled() {
    UserFilterDto userFilterDto = of(false);
    return userFilterDto;
  }


  public static UserFilterDto getById(String id) {
    UserFilterDto userFilterDto = of();
    userFilterDto.setId(id);
    return userFilterDto;
  }


  public static UserFilterDto getByFirstName(String firstName) {
    UserFilterDto userFilterDto = of();
    userFilterDto.setFirstName(firstName);
    return userFilterDto;
  }


  public static UserFilterDto getByLastName(String lastName) {
    UserFilterDto userFilterDto = of();
    userFilterDto.setLastName(lastName);
    return userFilterDto;
  }


  public static UserFilterDto getByEmail(String lastName) {
    UserFilterDto userFilterDto = of();
    userFilterDto.setEmail(lastName);
    return userFilterDto;
  }


  public static UserFilterDto getBySex(String sex) {
    UserFilterDto userFilterDto = of();
    userFilterDto.setSex(sex);
    return userFilterDto;
  }


  public static UserFilterDto getByBirthDate(LocalDate localDate) {
    UserFilterDto userFilterDto = of();
    userFilterDto.setBirthDate(String.valueOf(localDate));
    return userFilterDto;
  }


  public static UserFilterDto getFive() {
    UserFilterDto userFilterDto = of(5);
    userFilterDto.setEnabled(true);
    return userFilterDto;
  }


  public static UserFilterDto getSecondPage() {
    UserFilterDto userFilterDto = of(5);
    userFilterDto.setEnabled(true);
    userFilterDto.setPage(2);
    userFilterDto.setSort(User_.ID);
    userFilterDto.setDirection(Sort.Direction.ASC.toString());
    return userFilterDto;
  }


  public static UserFilterDto getSortedIdDesc() {
    UserFilterDto userFilterDto = of(true);
    userFilterDto.setSort(User_.ID);
    userFilterDto.setDirection(Sort.Direction.DESC.toString());
    return userFilterDto;
  }


  public static UserFilterDto getSortedByFirstNameAsc() {
    return ofEnabledSortedASCByParam(User_.FIRST_NAME);
  }


  public static UserFilterDto getSortedByLastNameAsc() {
    return ofEnabledSortedASCByParam(User_.LAST_NAME);
  }


  public static UserFilterDto getSortedByEmailAsc() {
    return ofEnabledSortedASCByParam(User_.EMAIL);
  }


  public static UserFilterDto getSortedByLoginAsc() {
    return ofEnabledSortedASCByParam(User_.LOGIN);
  }


  public static UserFilterDto getSortedBySexDesc() {
    UserFilterDto userFilterDto = ofEnabledSortedASCByParam(User_.SEX);
    userFilterDto.setDirection(Sort.Direction.DESC.toString());
    return userFilterDto;
  }


  public static UserFilterDto getSortedByBirthDateAsc() {
    return ofEnabledSortedASCByParam(User_.BIRTH_DATE);
  }


  public static UserFilterDto compositeFilter() {
    UserFilterDto userFilterDto = of(true);
    userFilterDto.setSex(Sex.WOMAN.getValue());
    userFilterDto.setBirthDate(GAGARINS_FLY_DAY_LOCAL_DATE.toString());
    return userFilterDto;

  }

}
