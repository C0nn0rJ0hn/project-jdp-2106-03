package com.kodilla.ecommercee.domain;

public class GroupDto {

    private Long id;
    private String name;

    public GroupDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupDto)) return false;

        GroupDto groupDto = (GroupDto) o;

        if (id != null ? !id.equals(groupDto.id) : groupDto.id != null) return false;
        return name != null ? name.equals(groupDto.name) : groupDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
