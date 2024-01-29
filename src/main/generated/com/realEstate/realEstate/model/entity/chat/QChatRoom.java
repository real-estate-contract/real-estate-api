package com.realEstate.realEstate.model.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = -1948596368L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    public final com.realEstate.realEstate.model.entity.QUser buyer;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<ChatMessage, QChatMessage> messages = this.<ChatMessage, QChatMessage>createList("messages", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final com.realEstate.realEstate.model.entity.QUser seller;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.realEstate.realEstate.model.entity.User, com.realEstate.realEstate.model.entity.QUser> users = this.<com.realEstate.realEstate.model.entity.User, com.realEstate.realEstate.model.entity.QUser>createList("users", com.realEstate.realEstate.model.entity.User.class, com.realEstate.realEstate.model.entity.QUser.class, PathInits.DIRECT2);

    public QChatRoom(String variable) {
        this(ChatRoom.class, forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.realEstate.realEstate.model.entity.QUser(forProperty("buyer")) : null;
        this.seller = inits.isInitialized("seller") ? new com.realEstate.realEstate.model.entity.QUser(forProperty("seller")) : null;
    }

}
