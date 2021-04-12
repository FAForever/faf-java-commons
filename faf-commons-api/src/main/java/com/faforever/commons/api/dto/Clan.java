package com.faforever.commons.api.dto;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Value
@SuperBuilder(toBuilder = true)
@ToString(exclude = "memberships")
@EqualsAndHashCode(callSuper = true)
@Type("clan")
public class Clan extends AbstractEntity {
    String name;
    String tag;
    String description;
    String tagColor;
    String websiteUrl;

    @Relationship("founder")
    Player founder;

    @Relationship("leader")
    Player leader;

    @Relationship("memberships")
    List<ClanMembership> memberships;
}
