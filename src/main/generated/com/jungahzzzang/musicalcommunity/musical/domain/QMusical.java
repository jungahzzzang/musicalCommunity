package com.jungahzzzang.musicalcommunity.musical.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMusical is a Querydsl query type for Musical
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMusical extends EntityPathBase<Musical> {

    private static final long serialVersionUID = 905341955L;

    public static final QMusical musical = new QMusical("musical");

    public final StringPath fcltynm = createString("fcltynm");

    public final NumberPath<Long> mcode = createNumber("mcode", Long.class);

    public final StringPath mt20id = createString("mt20id");

    public final StringPath poster = createString("poster");

    public final StringPath prfnm = createString("prfnm");

    public final StringPath prfstate = createString("prfstate");

    public QMusical(String variable) {
        super(Musical.class, forVariable(variable));
    }

    public QMusical(Path<? extends Musical> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMusical(PathMetadata metadata) {
        super(Musical.class, metadata);
    }

}

