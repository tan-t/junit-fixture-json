package com.tant.helper.exporter;

import static org.mockito.Mockito.doAnswer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RequiredArgsConstructor
@Slf4j
class JSONExporterImpl implements JSONExporter {
	
	private final ObjectMapper om;

	@Override
	public String exportFromBean(Object anyBean, String name) {
		Map<String,Object> m = new HashMap<>();
		m.put(name,anyBean);
		try {
			return om.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Stubber spyAndExportVoid(int index) {
        return doAnswer((Answer)invocation -> {
        	Object target = invocation.getArgument(index);
        	log.debug(om.writeValueAsString(target));
            return null;
        });
	}

	@Override
	public <T> Stubber spyAndExportRet(int index,
			Function<InvocationOnMock, T> returns) {
		 return doAnswer((Answer<T>)invocation -> {
	        	Object target = invocation.getArgument(index);
	        	log.debug(om.writeValueAsString(target));
	            return returns.apply(invocation);
	        });
	}

}
