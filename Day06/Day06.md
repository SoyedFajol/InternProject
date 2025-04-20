# Learn Cascade and Fetch Types in Spring Boot

## Date: 20th April 2025

## Soyed Md. Solaman Fajul
## Name: Learn Cascade, Cascade Types, @Column, Fetch Type, Difference, and Extend the Project

### 1. Cascade Types in JPA

Cascade operations are used to propagate certain actions from a parent entity to related entities. For example, if you delete a `Blog` post, you may want to delete its associated `BlogComment` entities as well.

There are several cascade types in JPA:

- **CascadeType.PERSIST**: When the parent entity is persisted, the child entity is also persisted.
- **CascadeType.MERGE**: When the parent entity is merged, the child entity is also merged.
- **CascadeType.REMOVE**: When the parent entity is removed, the child entity is also removed.
- **CascadeType.REFRESH**: When the parent entity is refreshed, the child entity is also refreshed.
- **CascadeType.DETACH**: When the parent entity is detached, the child entity is also detached.
- **CascadeType.ALL**: All of the above cascade operations.

```java
@Entity
public class Blog {
    @OneToMany(cascade = CascadeType.ALL)
    private List<BlogComment> comments;
}


## CRUD opration Extened