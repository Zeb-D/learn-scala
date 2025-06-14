// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/proto/route_guide.proto
// Protobuf Java Version: 4.29.3

package com.yd.scala.grpc.routeguide;

/**
 * <pre>
 * A RouteSummary is received in response to a RecordRoute rpc.
 *
 * It contains the number of individual points received, the number of
 * detected features, and the total distance covered as the cumulative sum of
 * the distance between each point.
 * </pre>
 *
 * Protobuf type {@code RouteSummary}
 */
public final class RouteSummary extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:RouteSummary)
    RouteSummaryOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      RouteSummary.class.getName());
  }
  // Use RouteSummary.newBuilder() to construct.
  private RouteSummary(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private RouteSummary() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_RouteSummary_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_RouteSummary_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.yd.scala.grpc.routeguide.RouteSummary.class, com.yd.scala.grpc.routeguide.RouteSummary.Builder.class);
  }

  public static final int POINT_COUNT_FIELD_NUMBER = 1;
  private int pointCount_ = 0;
  /**
   * <pre>
   * The number of points received.
   * </pre>
   *
   * <code>int32 point_count = 1;</code>
   * @return The pointCount.
   */
  @java.lang.Override
  public int getPointCount() {
    return pointCount_;
  }

  public static final int FEATURE_COUNT_FIELD_NUMBER = 2;
  private int featureCount_ = 0;
  /**
   * <pre>
   * The number of known features passed while traversing the route.
   * </pre>
   *
   * <code>int32 feature_count = 2;</code>
   * @return The featureCount.
   */
  @java.lang.Override
  public int getFeatureCount() {
    return featureCount_;
  }

  public static final int DISTANCE_FIELD_NUMBER = 3;
  private int distance_ = 0;
  /**
   * <pre>
   * The distance covered in metres.
   * </pre>
   *
   * <code>int32 distance = 3;</code>
   * @return The distance.
   */
  @java.lang.Override
  public int getDistance() {
    return distance_;
  }

  public static final int ELAPSED_TIME_FIELD_NUMBER = 4;
  private int elapsedTime_ = 0;
  /**
   * <pre>
   * The duration of the traversal in seconds.
   * </pre>
   *
   * <code>int32 elapsed_time = 4;</code>
   * @return The elapsedTime.
   */
  @java.lang.Override
  public int getElapsedTime() {
    return elapsedTime_;
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
    if (pointCount_ != 0) {
      output.writeInt32(1, pointCount_);
    }
    if (featureCount_ != 0) {
      output.writeInt32(2, featureCount_);
    }
    if (distance_ != 0) {
      output.writeInt32(3, distance_);
    }
    if (elapsedTime_ != 0) {
      output.writeInt32(4, elapsedTime_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (pointCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, pointCount_);
    }
    if (featureCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, featureCount_);
    }
    if (distance_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, distance_);
    }
    if (elapsedTime_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, elapsedTime_);
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
    if (!(obj instanceof com.yd.scala.grpc.routeguide.RouteSummary)) {
      return super.equals(obj);
    }
    com.yd.scala.grpc.routeguide.RouteSummary other = (com.yd.scala.grpc.routeguide.RouteSummary) obj;

    if (getPointCount()
        != other.getPointCount()) return false;
    if (getFeatureCount()
        != other.getFeatureCount()) return false;
    if (getDistance()
        != other.getDistance()) return false;
    if (getElapsedTime()
        != other.getElapsedTime()) return false;
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
    hash = (37 * hash) + POINT_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getPointCount();
    hash = (37 * hash) + FEATURE_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getFeatureCount();
    hash = (37 * hash) + DISTANCE_FIELD_NUMBER;
    hash = (53 * hash) + getDistance();
    hash = (37 * hash) + ELAPSED_TIME_FIELD_NUMBER;
    hash = (53 * hash) + getElapsedTime();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.yd.scala.grpc.routeguide.RouteSummary parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.yd.scala.grpc.routeguide.RouteSummary parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.yd.scala.grpc.routeguide.RouteSummary parseFrom(
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
  public static Builder newBuilder(com.yd.scala.grpc.routeguide.RouteSummary prototype) {
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
   * A RouteSummary is received in response to a RecordRoute rpc.
   *
   * It contains the number of individual points received, the number of
   * detected features, and the total distance covered as the cumulative sum of
   * the distance between each point.
   * </pre>
   *
   * Protobuf type {@code RouteSummary}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RouteSummary)
      com.yd.scala.grpc.routeguide.RouteSummaryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_RouteSummary_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_RouteSummary_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.yd.scala.grpc.routeguide.RouteSummary.class, com.yd.scala.grpc.routeguide.RouteSummary.Builder.class);
    }

    // Construct using com.yd.scala.grpc.routeguide.RouteSummary.newBuilder()
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
      pointCount_ = 0;
      featureCount_ = 0;
      distance_ = 0;
      elapsedTime_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.yd.scala.grpc.routeguide.RouteGuideProto.internal_static_RouteSummary_descriptor;
    }

    @java.lang.Override
    public com.yd.scala.grpc.routeguide.RouteSummary getDefaultInstanceForType() {
      return com.yd.scala.grpc.routeguide.RouteSummary.getDefaultInstance();
    }

    @java.lang.Override
    public com.yd.scala.grpc.routeguide.RouteSummary build() {
      com.yd.scala.grpc.routeguide.RouteSummary result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.yd.scala.grpc.routeguide.RouteSummary buildPartial() {
      com.yd.scala.grpc.routeguide.RouteSummary result = new com.yd.scala.grpc.routeguide.RouteSummary(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.yd.scala.grpc.routeguide.RouteSummary result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pointCount_ = pointCount_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.featureCount_ = featureCount_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.distance_ = distance_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.elapsedTime_ = elapsedTime_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.yd.scala.grpc.routeguide.RouteSummary) {
        return mergeFrom((com.yd.scala.grpc.routeguide.RouteSummary)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.yd.scala.grpc.routeguide.RouteSummary other) {
      if (other == com.yd.scala.grpc.routeguide.RouteSummary.getDefaultInstance()) return this;
      if (other.getPointCount() != 0) {
        setPointCount(other.getPointCount());
      }
      if (other.getFeatureCount() != 0) {
        setFeatureCount(other.getFeatureCount());
      }
      if (other.getDistance() != 0) {
        setDistance(other.getDistance());
      }
      if (other.getElapsedTime() != 0) {
        setElapsedTime(other.getElapsedTime());
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
            case 8: {
              pointCount_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              featureCount_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              distance_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              elapsedTime_ = input.readInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
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

    private int pointCount_ ;
    /**
     * <pre>
     * The number of points received.
     * </pre>
     *
     * <code>int32 point_count = 1;</code>
     * @return The pointCount.
     */
    @java.lang.Override
    public int getPointCount() {
      return pointCount_;
    }
    /**
     * <pre>
     * The number of points received.
     * </pre>
     *
     * <code>int32 point_count = 1;</code>
     * @param value The pointCount to set.
     * @return This builder for chaining.
     */
    public Builder setPointCount(int value) {

      pointCount_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The number of points received.
     * </pre>
     *
     * <code>int32 point_count = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPointCount() {
      bitField0_ = (bitField0_ & ~0x00000001);
      pointCount_ = 0;
      onChanged();
      return this;
    }

    private int featureCount_ ;
    /**
     * <pre>
     * The number of known features passed while traversing the route.
     * </pre>
     *
     * <code>int32 feature_count = 2;</code>
     * @return The featureCount.
     */
    @java.lang.Override
    public int getFeatureCount() {
      return featureCount_;
    }
    /**
     * <pre>
     * The number of known features passed while traversing the route.
     * </pre>
     *
     * <code>int32 feature_count = 2;</code>
     * @param value The featureCount to set.
     * @return This builder for chaining.
     */
    public Builder setFeatureCount(int value) {

      featureCount_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The number of known features passed while traversing the route.
     * </pre>
     *
     * <code>int32 feature_count = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearFeatureCount() {
      bitField0_ = (bitField0_ & ~0x00000002);
      featureCount_ = 0;
      onChanged();
      return this;
    }

    private int distance_ ;
    /**
     * <pre>
     * The distance covered in metres.
     * </pre>
     *
     * <code>int32 distance = 3;</code>
     * @return The distance.
     */
    @java.lang.Override
    public int getDistance() {
      return distance_;
    }
    /**
     * <pre>
     * The distance covered in metres.
     * </pre>
     *
     * <code>int32 distance = 3;</code>
     * @param value The distance to set.
     * @return This builder for chaining.
     */
    public Builder setDistance(int value) {

      distance_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The distance covered in metres.
     * </pre>
     *
     * <code>int32 distance = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearDistance() {
      bitField0_ = (bitField0_ & ~0x00000004);
      distance_ = 0;
      onChanged();
      return this;
    }

    private int elapsedTime_ ;
    /**
     * <pre>
     * The duration of the traversal in seconds.
     * </pre>
     *
     * <code>int32 elapsed_time = 4;</code>
     * @return The elapsedTime.
     */
    @java.lang.Override
    public int getElapsedTime() {
      return elapsedTime_;
    }
    /**
     * <pre>
     * The duration of the traversal in seconds.
     * </pre>
     *
     * <code>int32 elapsed_time = 4;</code>
     * @param value The elapsedTime to set.
     * @return This builder for chaining.
     */
    public Builder setElapsedTime(int value) {

      elapsedTime_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The duration of the traversal in seconds.
     * </pre>
     *
     * <code>int32 elapsed_time = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearElapsedTime() {
      bitField0_ = (bitField0_ & ~0x00000008);
      elapsedTime_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:RouteSummary)
  }

  // @@protoc_insertion_point(class_scope:RouteSummary)
  private static final com.yd.scala.grpc.routeguide.RouteSummary DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.yd.scala.grpc.routeguide.RouteSummary();
  }

  public static com.yd.scala.grpc.routeguide.RouteSummary getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RouteSummary>
      PARSER = new com.google.protobuf.AbstractParser<RouteSummary>() {
    @java.lang.Override
    public RouteSummary parsePartialFrom(
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

  public static com.google.protobuf.Parser<RouteSummary> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RouteSummary> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.yd.scala.grpc.routeguide.RouteSummary getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

