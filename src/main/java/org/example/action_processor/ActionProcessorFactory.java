package org.example.action_processor;

import org.example.action_processor.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ActionProcessorFactory {

    private Map<Integer, ActionProcessor> actions;

    public ActionProcessorFactory() {
        actions = new HashMap<>();
        actions.put(1, new CreateClientActionProcessor());
        actions.put(2, new FindAllClientActionProcessor());
        actions.put(3, new UpdateClientActionProcessor());
        actions.put(4, new DeleteClientActionProcessor());
        actions.put(5, new CreateProductActionProcessor());
        actions.put(6, new FindAllProductActionProcessor());
        actions.put(7, new UpdateProductActionProcessor());
        actions.put(8, new DeleteProductActionProcessor());
        actions.put(9, new CreateOrderActionProcess());
        actions.put(10, new FindAllOrderActionProcessor());
        actions.put(11, new DeleteOrderActionProcessor());
    }

    public ActionProcessor getActionProcessor(int action) {
        return actions.getOrDefault(action,null);
    }

}
