package com.realEstate.realEstate.model.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatImage is a Querydsl query type for ChatImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatImage extends EntityPathBase<ChatImage> {

    private static final long serialVersionUID = -285330074L;

    public static final QChatImage chatImage = new QChatImage("chatImage");

    public final QBaseImageEntity _super = new QBaseImageEntity(this);

    public final NumberPath<Integer> chatNo = createNumber("chatNo", Integer.class);

    //inherited
    public final StringPath imageName = _super.imageName;

    //inherited
    public final StringPath imageType = _super.imageType;

    //inherited
    public final StringPath imageUrl = _super.imageUrl;

    public final NumberPath<Integer> pictureNo = createNumber("pictureNo", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public QChatImage(String variable) {
        super(ChatImage.class, forVariable(variable));
    }

    public QChatImage(Path<? extends ChatImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatImage(PathMetadata metadata) {
        super(ChatImage.class, metadata);
    }

}

