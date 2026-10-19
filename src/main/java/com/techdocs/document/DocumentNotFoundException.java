package com.techdocs.document;
public class DocumentNotFoundException extends RuntimeException { public DocumentNotFoundException(Long id){super("Documento "+id+" não encontrado.");} }
