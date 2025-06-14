// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/proto/route_guide.proto
// Protobuf Java Version: 4.29.3

package com.yd.scala.grpc.routeguide;

public interface RectangleOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Rectangle)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * One corner of the rectangle.
   * </pre>
   *
   * <code>.Point lo = 1;</code>
   * @return Whether the lo field is set.
   */
  boolean hasLo();
  /**
   * <pre>
   * One corner of the rectangle.
   * </pre>
   *
   * <code>.Point lo = 1;</code>
   * @return The lo.
   */
  com.yd.scala.grpc.routeguide.Point getLo();
  /**
   * <pre>
   * One corner of the rectangle.
   * </pre>
   *
   * <code>.Point lo = 1;</code>
   */
  com.yd.scala.grpc.routeguide.PointOrBuilder getLoOrBuilder();

  /**
   * <pre>
   * The other corner of the rectangle.
   * </pre>
   *
   * <code>.Point hi = 2;</code>
   * @return Whether the hi field is set.
   */
  boolean hasHi();
  /**
   * <pre>
   * The other corner of the rectangle.
   * </pre>
   *
   * <code>.Point hi = 2;</code>
   * @return The hi.
   */
  com.yd.scala.grpc.routeguide.Point getHi();
  /**
   * <pre>
   * The other corner of the rectangle.
   * </pre>
   *
   * <code>.Point hi = 2;</code>
   */
  com.yd.scala.grpc.routeguide.PointOrBuilder getHiOrBuilder();
}
