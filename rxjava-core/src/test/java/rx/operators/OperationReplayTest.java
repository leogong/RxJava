/**
 * Copyright 2013 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rx.operators;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import rx.operators.OperationReplay.VirtualBoundedList;

public class OperationReplayTest {
    @Test
    public void testBoundedList() {
        VirtualBoundedList<Integer> list = new VirtualBoundedList<Integer>(3);
        
        list.add(1); // idx: 0
        list.add(2); // idx: 1
        list.add(3); // idx: 2
        
        Assert.assertEquals(3, list.size());

        list.add(4); // idx: 3

        Assert.assertEquals(3, list.size());
        Assert.assertEquals(Arrays.asList(2, 3, 4), list.toList());
        
        Assert.assertEquals(1, list.start());
        Assert.assertEquals(4, list.end());
        
        list.removeBefore(3);
        
        Assert.assertEquals(1, list.size());
        
        Assert.assertEquals(Arrays.asList(4), list.toList());

        Assert.assertEquals(3, list.start());
        Assert.assertEquals(4, list.end());
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testReadBefore() {
        VirtualBoundedList<Integer> list = new VirtualBoundedList<Integer>(3);
        
        list.add(1); // idx: 0
        list.add(2); // idx: 1
        list.add(3); // idx: 2
        list.add(4); // idx: 3
        
        list.get(0);
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testReadAfter() {
        VirtualBoundedList<Integer> list = new VirtualBoundedList<Integer>(3);
        
        list.add(1); // idx: 0
        list.add(2); // idx: 1
        list.add(3); // idx: 2
        list.add(4); // idx: 3
        
        list.get(4);
    }
}
