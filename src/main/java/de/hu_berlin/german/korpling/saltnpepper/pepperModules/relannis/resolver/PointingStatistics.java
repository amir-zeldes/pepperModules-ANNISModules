/*
 * Copyright 2015 Humboldt Univerity of Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hu_berlin.german.korpling.saltnpepper.pepperModules.relannis.resolver;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Statistics used for creating resolver entries for pointing relation components.
 *
 * @author Thomas Krause <krauseto@hu-berlin.de>
 */
public class PointingStatistics {
  
  private final Set<QName> layers = Collections.synchronizedSet(new HashSet<QName>());


  private final StatMultiMap<QName, QName> terminalAnno
          = new StatMultiMap<QName, QName>(layers);
  
  private final AtomicLong numberOfNodes = new AtomicLong(0l);
  
  public void addLayer(QName layer) {
    layers.add(layer);
  }
  
  
  public Set<QName> getLayers() {
    return new HashSet<QName>(layers);
  }
  
  public void addNodeCount() {
    numberOfNodes.incrementAndGet();
  }
  
  public void addNodeCount(long count) {
    numberOfNodes.addAndGet(count);
  }
  
  public long getNodeCount() {
    return numberOfNodes.get();
  }
  
  /**
   * Merges the information from the other statistics object.
   * 
   * The other object is not allowed to be modified while
   * executing this functions since no explicit locking will occur. This object
   * will be locked and suppports concurrent calls to this function.
   * @param other 
   */
  public void merge(PointingStatistics other) {
    numberOfNodes.addAndGet(other.numberOfNodes.get());
    layers.addAll(other.layers);
    terminalAnno.merge(other.terminalAnno);
  }

  public StatMultiMap<QName, QName> getTerminalAnno() {
    return terminalAnno;
  }
  
}