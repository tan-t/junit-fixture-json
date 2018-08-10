package com.tant.helper.exporter;

import java.util.function.Function;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Stubber;

public interface JSONExporter {
	public String exportFromBean(Object anyBean,String name);
	public Stubber spyAndExportVoid(int index);
	public <T> Stubber spyAndExportRet(int index,Function<InvocationOnMock,T> returns);
}
