// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Service.proto

package org.fbichat.stub;

public final class ServiceProto {
  private ServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_service_Report_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_service_Report_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_service_None_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_service_None_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rService.proto\022\007service\"W\n\006Report\022\024\n\014su" +
      "spectWords\030\001 \003(\t\022\017\n\007content\030\002 \001(\t\022\014\n\004fro" +
      "m\030\003 \001(\t\022\n\n\002to\030\004 \001(\t\022\014\n\004date\030\005 \001(\t\"\006\n\004Non" +
      "e24\n\007Service\022)\n\007analyze\022\017.service.Report" +
      "\032\r.service.NoneB\035\n\013org.fbichatB\014ServiceP" +
      "rotoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_service_Report_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_service_Report_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_service_Report_descriptor,
        new String[] { "SuspectWords", "Content", "From", "To", "Date", });
    internal_static_service_None_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_service_None_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_service_None_descriptor,
        new String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}