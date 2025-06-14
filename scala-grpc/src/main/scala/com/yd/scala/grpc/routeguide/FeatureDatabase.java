// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/proto/route_guide.proto
// Protobuf Java Version: 4.29.3

package com.yd.scala.grpc.routeguide;

/**
 * <pre>
 * Not used in the RPC.  Instead, this is here for the form serialized to disk.
 * </pre>
 *
 * Protobuf type {@code FeatureDatabase}
 */
public final class FeatureDatabase extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:FeatureDatabase)
    FeatureDatabaseOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      FeatureDatabase.class.getName());
  }
  // Use FeatureDatabase.newBuilder() to construct.
  private FeatureDatabase(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private FeatureDatabase() {
    feature_ = java.util.Collections.emptyList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_FeatureDatabase_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_FeatureDatabase_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.yd.scala.grpc.routeguide.FeatureDatabase.class, com.yd.scala.grpc.routeguide.FeatureDatabase.Builder.class);
  }

  public static final int FEATURE_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<com.yd.scala.grpc.routeguide.Feature> feature_;
  /**
   * <code>repeated .Feature feature = 1;</code>
   */
  @java.lang.Override
  public java.util.List<com.yd.scala.grpc.routeguide.Feature> getFeatureList() {
    return feature_;
  }
  /**
   * <code>repeated .Feature feature = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.yd.scala.grpc.routeguide.FeatureOrBuilder> 
      getFeatureOrBuilderList() {
    return feature_;
  }
  /**
   * <code>repeated .Feature feature = 1;</code>
   */
  @java.lang.Override
  public int getFeatureCount() {
    return feature_.size();
  }
  /**
   * <code>repeated .Feature feature = 1;</code>
   */
  @java.lang.Override
  public com.yd.scala.grpc.routeguide.Feature getFeature(int index) {
    return feature_.get(index);
  }
  /**
   * <code>repeated .Feature feature = 1;</code>
   */
  @java.lang.Override
  public com.yd.scala.grpc.routeguide.FeatureOrBuilder getFeatureOrBuilder(
      int index) {
    return feature_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < feature_.size(); i++) {
      output.writeMessage(1, feature_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < feature_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, feature_.get(i));
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.yd.scala.grpc.routeguide.FeatureDatabase)) {
      return super.equals(obj);
    }
    com.yd.scala.grpc.routeguide.FeatureDatabase other = (com.yd.scala.grpc.routeguide.FeatureDatabase) obj;

    if (!getFeatureList()
        .equals(other.getFeatureList())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getFeatureCount() > 0) {
      hash = (37 * hash) + FEATURE_FIELD_NUMBER;
      hash = (53 * hash) + getFeatureList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.yd.scala.grpc.routeguide.FeatureDatabase parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.yd.scala.grpc.routeguide.FeatureDatabase prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Not used in the RPC.  Instead, this is here for the form serialized to disk.
   * </pre>
   *
   * Protobuf type {@code FeatureDatabase}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:FeatureDatabase)
      com.yd.scala.grpc.routeguide.FeatureDatabaseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_FeatureDatabase_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_FeatureDatabase_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yd.scala.grpc.routeguide.FeatureDatabase.class, com.yd.scala.grpc.routeguide.FeatureDatabase.Builder.class);
    }

    // Construct using com.yd.scala.grpc.routeguide.FeatureDatabase.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      if (featureBuilder_ == null) {
        feature_ = java.util.Collections.emptyList();
      } else {
        feature_ = null;
        featureBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_FeatureDatabase_descriptor;
    }

    @java.lang.Override
    public com.yd.scala.grpc.routeguide.FeatureDatabase getDefaultInstanceForType() {
      return com.yd.scala.grpc.routeguide.FeatureDatabase.getDefaultInstance();
    }

    @java.lang.Override
    public com.yd.scala.grpc.routeguide.FeatureDatabase build() {
      com.yd.scala.grpc.routeguide.FeatureDatabase result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.yd.scala.grpc.routeguide.FeatureDatabase buildPartial() {
      com.yd.scala.grpc.routeguide.FeatureDatabase result = new com.yd.scala.grpc.routeguide.FeatureDatabase(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.yd.scala.grpc.routeguide.FeatureDatabase result) {
      if (featureBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          feature_ = java.util.Collections.unmodifiableList(feature_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.feature_ = feature_;
      } else {
        result.feature_ = featureBuilder_.build();
      }
    }

    private void buildPartial0(com.yd.scala.grpc.routeguide.FeatureDatabase result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.yd.scala.grpc.routeguide.FeatureDatabase) {
        return mergeFrom((com.yd.scala.grpc.routeguide.FeatureDatabase)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.yd.scala.grpc.routeguide.FeatureDatabase other) {
      if (other == com.yd.scala.grpc.routeguide.FeatureDatabase.getDefaultInstance()) return this;
      if (featureBuilder_ == null) {
        if (!other.feature_.isEmpty()) {
          if (feature_.isEmpty()) {
            feature_ = other.feature_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureFeatureIsMutable();
            feature_.addAll(other.feature_);
          }
          onChanged();
        }
      } else {
        if (!other.feature_.isEmpty()) {
          if (featureBuilder_.isEmpty()) {
            featureBuilder_.dispose();
            featureBuilder_ = null;
            feature_ = other.feature_;
            bitField0_ = (bitField0_ & ~0x00000001);
            featureBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getFeatureFieldBuilder() : null;
          } else {
            featureBuilder_.addAllMessages(other.feature_);
          }
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              com.yd.scala.grpc.routeguide.Feature m =
                  input.readMessage(
                      com.yd.scala.grpc.routeguide.Feature.parser(),
                      extensionRegistry);
              if (featureBuilder_ == null) {
                ensureFeatureIsMutable();
                feature_.add(m);
              } else {
                featureBuilder_.addMessage(m);
              }
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.util.List<com.yd.scala.grpc.routeguide.Feature> feature_ =
      java.util.Collections.emptyList();
    private void ensureFeatureIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        feature_ = new java.util.ArrayList<com.yd.scala.grpc.routeguide.Feature>(feature_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        com.yd.scala.grpc.routeguide.Feature, com.yd.scala.grpc.routeguide.Feature.Builder, com.yd.scala.grpc.routeguide.FeatureOrBuilder> featureBuilder_;

    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public java.util.List<com.yd.scala.grpc.routeguide.Feature> getFeatureList() {
      if (featureBuilder_ == null) {
        return java.util.Collections.unmodifiableList(feature_);
      } else {
        return featureBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public int getFeatureCount() {
      if (featureBuilder_ == null) {
        return feature_.size();
      } else {
        return featureBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public com.yd.scala.grpc.routeguide.Feature getFeature(int index) {
      if (featureBuilder_ == null) {
        return feature_.get(index);
      } else {
        return featureBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder setFeature(
        int index, com.yd.scala.grpc.routeguide.Feature value) {
      if (featureBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFeatureIsMutable();
        feature_.set(index, value);
        onChanged();
      } else {
        featureBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder setFeature(
        int index, com.yd.scala.grpc.routeguide.Feature.Builder builderForValue) {
      if (featureBuilder_ == null) {
        ensureFeatureIsMutable();
        feature_.set(index, builderForValue.build());
        onChanged();
      } else {
        featureBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder addFeature(com.yd.scala.grpc.routeguide.Feature value) {
      if (featureBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFeatureIsMutable();
        feature_.add(value);
        onChanged();
      } else {
        featureBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder addFeature(
        int index, com.yd.scala.grpc.routeguide.Feature value) {
      if (featureBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFeatureIsMutable();
        feature_.add(index, value);
        onChanged();
      } else {
        featureBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder addFeature(
        com.yd.scala.grpc.routeguide.Feature.Builder builderForValue) {
      if (featureBuilder_ == null) {
        ensureFeatureIsMutable();
        feature_.add(builderForValue.build());
        onChanged();
      } else {
        featureBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder addFeature(
        int index, com.yd.scala.grpc.routeguide.Feature.Builder builderForValue) {
      if (featureBuilder_ == null) {
        ensureFeatureIsMutable();
        feature_.add(index, builderForValue.build());
        onChanged();
      } else {
        featureBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder addAllFeature(
        java.lang.Iterable<? extends com.yd.scala.grpc.routeguide.Feature> values) {
      if (featureBuilder_ == null) {
        ensureFeatureIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, feature_);
        onChanged();
      } else {
        featureBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder clearFeature() {
      if (featureBuilder_ == null) {
        feature_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        featureBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public Builder removeFeature(int index) {
      if (featureBuilder_ == null) {
        ensureFeatureIsMutable();
        feature_.remove(index);
        onChanged();
      } else {
        featureBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public com.yd.scala.grpc.routeguide.Feature.Builder getFeatureBuilder(
        int index) {
      return getFeatureFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public com.yd.scala.grpc.routeguide.FeatureOrBuilder getFeatureOrBuilder(
        int index) {
      if (featureBuilder_ == null) {
        return feature_.get(index);  } else {
        return featureBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public java.util.List<? extends com.yd.scala.grpc.routeguide.FeatureOrBuilder> 
         getFeatureOrBuilderList() {
      if (featureBuilder_ != null) {
        return featureBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(feature_);
      }
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public com.yd.scala.grpc.routeguide.Feature.Builder addFeatureBuilder() {
      return getFeatureFieldBuilder().addBuilder(
          com.yd.scala.grpc.routeguide.Feature.getDefaultInstance());
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public com.yd.scala.grpc.routeguide.Feature.Builder addFeatureBuilder(
        int index) {
      return getFeatureFieldBuilder().addBuilder(
          index, com.yd.scala.grpc.routeguide.Feature.getDefaultInstance());
    }
    /**
     * <code>repeated .Feature feature = 1;</code>
     */
    public java.util.List<com.yd.scala.grpc.routeguide.Feature.Builder> 
         getFeatureBuilderList() {
      return getFeatureFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        com.yd.scala.grpc.routeguide.Feature, com.yd.scala.grpc.routeguide.Feature.Builder, com.yd.scala.grpc.routeguide.FeatureOrBuilder> 
        getFeatureFieldBuilder() {
      if (featureBuilder_ == null) {
        featureBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            com.yd.scala.grpc.routeguide.Feature, com.yd.scala.grpc.routeguide.Feature.Builder, com.yd.scala.grpc.routeguide.FeatureOrBuilder>(
                feature_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        feature_ = null;
      }
      return featureBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:FeatureDatabase)
  }

  // @@protoc_insertion_point(class_scope:FeatureDatabase)
  private static final com.yd.scala.grpc.routeguide.FeatureDatabase DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.yd.scala.grpc.routeguide.FeatureDatabase();
  }

  public static com.yd.scala.grpc.routeguide.FeatureDatabase getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FeatureDatabase>
      PARSER = new com.google.protobuf.AbstractParser<FeatureDatabase>() {
    @java.lang.Override
    public FeatureDatabase parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<FeatureDatabase> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FeatureDatabase> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.yd.scala.grpc.routeguide.FeatureDatabase getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

