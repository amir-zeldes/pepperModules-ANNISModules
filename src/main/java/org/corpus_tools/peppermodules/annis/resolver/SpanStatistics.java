/**
 * Copyright 2015 Humboldt-Universität zu Berlin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.corpus_tools.peppermodules.annis.resolver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.corpus_tools.salt.core.SAnnotation;

/**
 * Statistics used for creating resolver entries for dominance components.
 *
 * @author Thomas Krause <krauseto@hu-berlin.de>
 */
public class SpanStatistics {

  private final Set<String> layers = Collections.synchronizedSet(new HashSet<String>());
  
  private final StatMultiMap<String, QName> nodeAnnotations;
  
  
  public SpanStatistics() {
    nodeAnnotations = new StatMultiMap<>(layers);
  }

  public void addLayer(String layerName) {
    layers.add(layerName);
  }
  
  public Set<String> getLayers() {
    return new HashSet<>(layers);
  }
  
  public void addNodeAnno(String layer, Set<SAnnotation> annos) {
    if (annos != null) {
      for (SAnnotation a : annos) {
        QName qname = new QName(a.getNamespace(), a.getName());
         this.nodeAnnotations.add(layer, qname);
      }
    }
  }
  
  public Set<QName> getNodeAnnotations(String layer) {
    return new TreeSet<>(this.nodeAnnotations.get(layer));
  }
  
  
  /**
   * Merges the information from the other statistics object.
   * 
   * The other object is not allowed to be modified while
   * executing this functions since no explicit locking will occur. This object
   * will be locked and suppports concurrent calls to this function.
   * @param other 
   */
  public void merge(SpanStatistics other) {
    layers.addAll(other.layers);
    nodeAnnotations.merge(other.nodeAnnotations);
  }
}
